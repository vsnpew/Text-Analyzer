package com.vsnpew.BookAnalyzer.Repositories;

import com.vsnpew.BookAnalyzer.Entities.WrongSymbol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymbolRepository extends JpaRepository<WrongSymbol, Integer> {
}
